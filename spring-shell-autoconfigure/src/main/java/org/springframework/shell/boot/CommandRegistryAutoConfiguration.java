/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.shell.boot;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.CommandRegistry;
import org.springframework.shell.ConfigurableCommandRegistry;
import org.springframework.shell.MethodTargetRegistrar;

@Configuration(proxyBeanMethods = false)
public class CommandRegistryAutoConfiguration {

	@Bean
	public CommandRegistry commandRegistry(
			ObjectProvider<MethodTargetRegistrar> methodTargetRegistrars) {
		ConfigurableCommandRegistry registry = new ConfigurableCommandRegistry();
		methodTargetRegistrars.orderedStream().forEach(resolver -> {
			resolver.register(registry);
		});
		return registry;
	}
}
