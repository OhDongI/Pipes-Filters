/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import java.io.EOFException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public abstract class CommonFilterImpl implements CommonFilter {
    protected PipedInputStream in = new PipedInputStream();
    protected PipedOutputStream out = new PipedOutputStream();
    private boolean isConnected = false;

    public void connectOutputTo(CommonFilter nextFilter) throws IOException {
        if (!isConnected) {
            out.connect(nextFilter.getPipedInputStream());
            isConnected = true;
        } else {
            throw new IOException("Output stream already connected.");
        }
    }

    public void connectInputTo(CommonFilter previousFilter) throws IOException {
        in.connect(previousFilter.getPipedOutputStream());
    }

    public PipedInputStream getPipedInputStream() {
        return in;
    }

    public PipedOutputStream getPipedOutputStream() {
        return out;
    }

    abstract public boolean specificComputationForFilter() throws IOException;

    public void run() {
        try {
            specificComputationForFilter();
        } catch (IOException e) {
            if (e instanceof EOFException) return;
            else System.out.println(e);
        } finally {
            closePorts();
        }
    }

    private void closePorts() {
        try {
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}