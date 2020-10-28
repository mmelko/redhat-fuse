/*
 * Copyright 2005-2020 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.fuse.mvnplugins.patch.extensions;

import org.codehaus.plexus.component.annotations.Component;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.spi.connector.layout.RepositoryLayout;
import org.eclipse.aether.spi.connector.layout.RepositoryLayoutFactory;
import org.eclipse.aether.spi.locator.Service;
import org.eclipse.aether.spi.locator.ServiceLocator;
import org.eclipse.aether.transfer.NoRepositoryLayoutException;

@Component(role = RepositoryLayoutFactory.class)
public class ZipRepositoryLayoutFactory implements RepositoryLayoutFactory, Service {

    @Override
    public void initService(ServiceLocator locator) {
        locator.getService(RepositoryLayout.class);
    }

    @Override
    public RepositoryLayout newInstance(RepositorySystemSession repositorySystemSession, final RemoteRepository repository) throws NoRepositoryLayoutException {
        if (!"zip".equals(repository.getContentType())) {
            throw new NoRepositoryLayoutException(repository);
        }
        return new ZipRepositoryLayout(repository);
    }

    @Override
    public float getPriority() {
        return 0;
    }

}
