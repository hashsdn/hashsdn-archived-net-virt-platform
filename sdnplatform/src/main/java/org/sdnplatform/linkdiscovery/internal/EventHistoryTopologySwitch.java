/*
 * Copyright (c) 2013 Big Switch Networks, Inc.
 *
 * Licensed under the Eclipse Public License, Version 1.0 (the
 * "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 *      http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.sdnplatform.linkdiscovery.internal;


import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.sdnplatform.core.web.serializers.DPIDSerializer;
import org.sdnplatform.core.web.serializers.IPv4Serializer;

/***
 * Topology Switch event history related classes and members
 * @author subrata
 *
 */
public class EventHistoryTopologySwitch {
    // The following fields are not stored as String to save memory
    // They should be converted to appropriate human-readable strings by 
    // the front end (e.g. in cli in Python)
    public long     dpid;
    public int  ipv4Addr;
    public int    l4Port;
    public String   reason;
    
    @JsonProperty("Switch")
    @JsonSerialize(using=DPIDSerializer.class)
    public long getDpid() {
        return dpid;
    }
    @JsonProperty("IpAddr")
    @JsonSerialize(using=IPv4Serializer.class)
    public int getIpv4Addr() {
        return ipv4Addr;
    }
    @JsonProperty("Port")
    public int getL4Port() {
        return l4Port;
    }
    @JsonProperty("Reason")
    public String getReason() {
        return reason;
    }
    
    
}
