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
package org.springframework.shell.standard;

import java.util.stream.Stream;

import org.jline.terminal.Terminal;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.shell.CommandRegistry;
import org.springframework.shell.ParameterResolver;
import org.springframework.shell.Shell;

/**
 * Base class helping to build shell components.
 *
 * @author Janne Valkealahti
 */
public class AbstractShellComponent implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    private ObjectProvider<Shell> shellProvider;

    private ObjectProvider<Terminal> terminalProvider;

    private ObjectProvider<CommandRegistry> commandRegistryProvider;

    private ObjectProvider<ParameterResolver> parameterResolverProvider;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        shellProvider = applicationContext.getBeanProvider(Shell.class);
        terminalProvider = applicationContext.getBeanProvider(Terminal.class);
        commandRegistryProvider = applicationContext.getBeanProvider(CommandRegistry.class);
        parameterResolverProvider = applicationContext.getBeanProvider(ParameterResolver.class);
    }

    protected Shell getShell() {
        return shellProvider.getObject();
    }

    protected Terminal getTerminal() {
        return terminalProvider.getObject();
    }

    protected CommandRegistry getCommandRegistry() {
        return commandRegistryProvider.getObject();
    }

    protected Stream<ParameterResolver> getParameterResolver() {
        return parameterResolverProvider.orderedStream();
    }
}
