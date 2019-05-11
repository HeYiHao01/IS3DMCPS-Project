package com.jeesite.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.da.AccessBase;
import org.openscada.opc.lib.da.DataCallback;
import org.openscada.opc.lib.da.Group;
import org.openscada.opc.lib.da.Item;
import org.openscada.opc.lib.da.ItemState;
import org.openscada.opc.lib.da.Server;
import org.openscada.opc.lib.da.SyncAccess;

public class opcmain {

	public static void main(String[] args) throws Exception{
	    final ConnectionInformation ci = new ConnectionInformation();
	    ci.setHost("localhost");
	    ci.setUser("Administrator");
	    ci.setPassword("gl");
	    ci.setProgId("Matrikon.OPC.Simulation.1");
	    final String itemId = "Bucket Brigade.Int4";
	    // create a new server
	    final Server server = new Server(ci, Executors.newSingleThreadScheduledExecutor());
	    try {
	        // connect to server
	        server.connect();
	        System.out.println("连接成功");
	        final AccessBase access = new SyncAccess(server, 500);
	        access.addItem(itemId, new DataCallback() {
	            @Override
	            public void changed(Item item, ItemState state) {
	                // also dump value
	                try {
	                    if (state.getValue().getType() == JIVariant.VT_UI4) {
	                        System.out.println("<<< " + state + " / value = " + state.getValue().getObjectAsUnsigned().getValue());
	                    } else {
	                        System.out.println("<<< " + state + " / ------value = " + state.getValue().getObject());
	                    }
	                } catch (JIException e) {
	                    e.printStackTrace();
	                }
	            }
	        });

	        // Add a new group
	        final Group group = server.addGroup("test");
	        // Add a new item to the group
	        final Item item = group.addItem(itemId);

	        // start reading
	        access.bind();

	        // add a thread for writing a value every 3 seconds
	        ScheduledExecutorService writeThread = Executors.newSingleThreadScheduledExecutor();
	        final AtomicInteger i = new AtomicInteger(0);
	        writeThread.scheduleWithFixedDelay(new Runnable() {
	            @Override
	            public void run() {
	                final JIVariant value = new JIVariant(i.incrementAndGet());
	                try {
	                    System.out.println(">>> " + "writing value " + i.get());
	                    item.write(value);
	                } catch (JIException e) {
	                    e.printStackTrace();
	                }
	            }
	        }, 5, 3, TimeUnit.SECONDS);

	        // wait a little bit
	        Thread.sleep(20 * 1000);
	        writeThread.shutdownNow();
	        // stop reading
	        access.unbind();
	    } catch (final JIException e) {
	        System.out.println(String.format("%08X: %s", e.getErrorCode(), server.getErrorMessage(e.getErrorCode())));
	    }
	}

}
