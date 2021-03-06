/*
   Copyright 2018 Profitbricks GmbH

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.profitbricks.sdk.model

import com.profitbricks.sdk.annotation.*
import groovy.transform.*

/**
 * a LAN POGO
 * @see <a href="https://devops.profitbricks.com/api/cloud/v4/#lans">Cloud API reference</a>
 *
 * @author fudge <frank.geusch@profitbricks.com>
 */
@ToString(includeNames = true, ignoreNulls = true, includeSuperProperties = true, includePackage = false, excludes = ['resource', 'dataCenter'])
@EqualsAndHashCode(callSuper = true)
final class LAN extends ModelBase {
    DataCenter dataCenter
    @Creatable @Updatable
    String name
    @Creatable @Updatable
    boolean _public = true
    @Updatable
    List<IPFailover> ipFailover

    @ToString(includeNames = true, ignoreNulls = true, includePackage = false)
    static class IPFailover {
        String ip, nicUuid
    }

    @Override
    final getId() { super.id as Integer }

    @Override
    final LAN create(final Map options = [:]) { (super.create(options) as LAN)?.with(dataCenter) }

    @Override
    final LAN read(final id = id, final Map options = [:]) { (super.read(id, options) as LAN)?.with(dataCenter) }

    @Override
    final String getResource() { "${dataCenter.resource}/${dataCenter.id}/lans" }

    private final LAN with(final DataCenter dc) { dataCenter = dc; this }
}
