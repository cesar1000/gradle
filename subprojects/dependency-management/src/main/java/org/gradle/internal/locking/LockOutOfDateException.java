/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.internal.locking;

import org.gradle.api.artifacts.component.ModuleComponentIdentifier;

import java.util.Collection;

public class LockOutOfDateException extends RuntimeException {

    public static LockOutOfDateException createLockOutOfDateException(Iterable<String> errors) {
        StringBuilder builder = new StringBuilder("Dependency lock out of date:\n");
        for (String error : errors) {
            builder.append("\t").append(error).append("\n");
        }
        return new LockOutOfDateException(builder.toString());
    }

    public static LockOutOfDateException createLockOutOfDateExceptionStrictMode(Collection<ModuleComponentIdentifier> extraModules) {
        StringBuilder builder = new StringBuilder("Dependency lock out of date (strict mode):\n");
        for (ModuleComponentIdentifier extraModule : extraModules) {
            builder.append("Module missing from lock file: ").append(extraModule.getGroup()).append(":").append(extraModule.getModule()).append(":").append(extraModule.getVersion()).append("\n");
        }
        return new LockOutOfDateException(builder.toString());
    }

    private LockOutOfDateException(String message) {
        super(message);
    }
}
