/*
 * Copyright (c) 2011-2015, fortiss GmbH.
 * Licensed under the Apache License, Version 2.0.
 *
 * Use, modification and distribution are subject to the terms specified
 * in the accompanying license file LICENSE.txt located at the root directory
 * of this software distribution.
 */
package org.fortiss.smg.actuatormaster.impl;import org.fortiss.smg.actuatormaster.api.ActuatorMasterQueueNames;import org.fortiss.smg.actuatormaster.api.IActuatorMaster;import org.fortiss.smg.ambulance.api.AmbulanceInterface;import org.fortiss.smg.ambulance.api.AmbulanceMessenger;import org.fortiss.smg.ambulance.api.AmbulanceMessenger.IOnRegisterComplete;import org.fortiss.smg.ambulance.api.AmbulanceQueueNames;import org.fortiss.smg.containermanager.api.ContainerManagerInterface;import org.fortiss.smg.containermanager.api.ContainerManagerQueueNames;import org.fortiss.smg.remoteframework.lib.DefaultProxy;import org.fortiss.smg.remoteframework.lib.DefaultServer;import org.osgi.framework.BundleActivator;import org.osgi.framework.BundleContext;import org.slf4j.LoggerFactory;public class ActuatorMasterActivator implements BundleActivator {	DefaultServer<IActuatorMaster> server;	ActuatorMasterImpl impl;	// Logger from sl4j	private static org.slf4j.Logger logger = LoggerFactory			.getLogger(ActuatorMasterActivator.class);	@Override	public void start(BundleContext context) throws Exception {		// register here your services etc.		// DO NOT start heavy operations here - use threads						impl = new ActuatorMasterImpl();		server = new DefaultServer<IActuatorMaster>(IActuatorMaster.class,				impl,				ActuatorMasterQueueNames.getActuatorMasterInterfaceQueue());		server.init();								logger.info("ActuatorMaster is trying to connect to ambulance");		AmbulanceMessenger.register("awesome-act-master", ActuatorMasterQueueNames.getActuatorMasterInterfaceQueue(), new IOnRegisterComplete() {						@Override			public void onRegisterComplete() {				logger.info("ActuatorMaster connected to ambulance. ALIVE");			}		});	}	@Override	public void stop(BundleContext context) throws Exception {		// REMEMBER to destroy all resources, threads and do cleanup		server.destroy();		logger.info("ActuatorMaster is dead");	}}