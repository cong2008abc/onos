/*
 * Copyright 2017-present Open Networking Laboratory
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
package org.onosproject.mapping.web;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.onosproject.codec.CodecService;
import org.onosproject.mapping.addresses.MappingAddress;
import org.onosproject.mapping.instructions.MappingInstruction;
import org.onosproject.mapping.web.codec.MappingAddressCodec;
import org.onosproject.mapping.web.codec.MappingInstructionCodec;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Implementation of the JSON codec brokering service for mapping primitives.
 */
@Component(immediate = true)
public class MappingCodecRegistrator {

    private final Logger log = getLogger(getClass());

    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    public CodecService codecService;

    @Activate
    public void activate() {
        codecService.registerCodec(MappingAddress.class, new MappingAddressCodec());
        codecService.registerCodec(MappingInstruction.class, new MappingInstructionCodec());

        log.info("Started");
    }

    @Deactivate
    public void deactivate() {
        codecService.unregisterCodec(MappingAddress.class);
        codecService.unregisterCodec(MappingInstruction.class);

        log.info("Stopped");
    }
}
