/*
 * Copyright (c) 2011-2015, fortiss GmbH.
 * Licensed under the Apache License, Version 2.0.
 *
 * Use, modification and distribution are subject to the terms specified
 * in the accompanying license file LICENSE.txt located at the root directory
 * of this software distribution.
 */
package org.fortiss.smg.optimizer.impl;import org.fortiss.smg.optimizer.api.OptimizerInterface;import org.fortiss.smg.optimizer.api.OptimizerQueueNames;import org.fortiss.smg.remoteframework.lib.DefaultServer;import org.osgi.framework.BundleActivator;import org.osgi.framework.BundleContext;import org.slf4j.LoggerFactory;public class OptimizerActivator implements BundleActivator {    DefaultServer<OptimizerInterface> server;    OptimizerImpl impl;    // Logger from sl4j    private static org.slf4j.Logger logger = LoggerFactory.getLogger(OptimizerActivator.class);    @Override    public void start(BundleContext context) throws Exception {        // register here your services etc.        // DO NOT start heavy operations here - use threads        impl = new OptimizerImpl();        server = new DefaultServer<OptimizerInterface>(OptimizerInterface.class, impl, OptimizerQueueNames.getOptimizerInterfaceQueue());        server.init();        logger.info("Optimizer is alive");    }    @Override    public void stop(BundleContext context) throws Exception {        // REMEMBER to destroy all resources, threads and do cleanup        server.destroy();        logger.info("Optimizer is dead");    }}