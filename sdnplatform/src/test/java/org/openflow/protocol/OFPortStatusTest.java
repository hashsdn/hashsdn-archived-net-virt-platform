/*
 * Copyright (c) 2013 Big Switch Networks, Inc.
 * Copyright (c) 2008 The Board of Trustees of The Leland Stanford Junior University 
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

package org.openflow.protocol;

import junit.framework.TestCase;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.openflow.protocol.OFPortStatus.OFPortReason;
import org.openflow.util.OFTestCase;

public class OFPortStatusTest extends OFTestCase {
    public void testWriteRead() throws Exception {
        OFPortStatus msg = (OFPortStatus) messageFactory
                .getMessage(OFType.PORT_STATUS);
        msg.setDesc(new OFPhysicalPort());
        msg.getDesc().setHardwareAddress(new byte[6]);
        msg.getDesc().setName("eth0");
        msg.setReason((byte) OFPortReason.OFPPR_ADD.ordinal());
        ChannelBuffer bb = ChannelBuffers.dynamicBuffer();
        bb.clear();
        msg.writeTo(bb);
        msg.readFrom(bb);
        TestCase.assertEquals(OFType.PORT_STATUS, msg.getType());
        TestCase.assertEquals((byte) OFPortReason.OFPPR_ADD.ordinal(), msg
                .getReason());
        TestCase.assertNotNull(msg.getDesc());
    }
}
