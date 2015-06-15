/*
 * Copyright (c) 2011-2015, fortiss GmbH.
 * Licensed under the Apache License, Version 2.0.
 *
 * Use, modification and distribution are subject to the terms specified
 * in the accompanying license file LICENSE.txt located at the root directory
 * of this software distribution.
 */
package org.fortiss.smg.containermanager.api.devices;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ContainerType {	
	COMPLEX, FLOOR, ROOM, BUILDING, DEVICE, DEVICEGATEWAY, UNKNOWN;
	
	
	public static ContainerType fromSting(String x) {
		for (ContainerType type : ContainerType.values()) {
			if (type.toString().equals(x)) {
				return type;
			}
		}
		return ContainerType.UNKNOWN;
    }
	
}