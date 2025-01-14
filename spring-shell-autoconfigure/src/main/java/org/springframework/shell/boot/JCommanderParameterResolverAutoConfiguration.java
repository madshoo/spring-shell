/*
 * Copyright 2017-2021 the original author or authors.
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

import com.beust.jcommander.JCommander;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jcommander.JCommanderParameterResolver;
import org.springframework.context.annotation.Bean;

/**
 * Registers JCommanderParameterResolver and supporting beans as appropriate.
 *
 * @author Eric Bottard
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({ JCommander.class, JCommanderParameterResolver.class })
public class JCommanderParameterResolverAutoConfiguration {

	@Bean
	public JCommanderParameterResolver jCommanderParameterResolver() {
		return new JCommanderParameterResolver();
	}
}
