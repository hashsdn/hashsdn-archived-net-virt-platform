/*
 * Copyright (c) 2011,2013 Big Switch Networks, Inc.
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
 *
 * This file incorporates work covered by the following copyright and
 * permission notice:
 *
 *    Originally created by David Erickson, Stanford University 
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the
 *    License. You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing,
 *    software distributed under the License is distributed on an "AS
 *    IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 *    express or implied. See the License for the specific language
 *    governing permissions and limitations under the License. 
 */

package org.sdnplatform.core.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


import org.restlet.resource.Get;
import org.sdnplatform.counter.CounterValue;
import org.sdnplatform.counter.ICounter;

public class CounterResource extends CounterResourceBase {
    @Get("json")
    public Map<String, Object> retrieve() {
        String counterTitle = 
            (String) getRequestAttributes().get("counterTitle");
        Map<String, Object> model = new HashMap<String,Object>();
        CounterValue v;
        if (counterTitle.equalsIgnoreCase("all")) {
            Map<String, ICounter> counters = this.counterStore.getAll();
            if (counters != null) {
                Iterator<Map.Entry<String, ICounter>> it = 
                    counters.entrySet().iterator();
                while (it.hasNext()) {
                    Entry<String, ICounter> entry = it.next();
                    String counterName = entry.getKey();
                    v = entry.getValue().getCounterValue();

                    if (CounterValue.CounterType.LONG == v.getType()) {
                        model.put(counterName, v.getLong());
                    } else if (v.getType() == CounterValue.CounterType.DOUBLE) {
                        model.put(counterName, v.getDouble());
                    }   
                }   
            }   
        } else {
            ICounter counter = this.counterStore.getCounter(counterTitle);
            if (counter != null) {
                v = counter.getCounterValue();
            } else {
                v = new CounterValue(CounterValue.CounterType.LONG);
            }   

            if (CounterValue.CounterType.LONG == v.getType()) {
                model.put(counterTitle, v.getLong());
            } else if (v.getType() == CounterValue.CounterType.DOUBLE) {
                model.put(counterTitle, v.getDouble());
            }   
        }
        return model;
    }
}
