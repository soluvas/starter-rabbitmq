package org.soluvas.tutorial.rabbitmq.core;

/**
 * <a href="http://www.hydra-cg.com/spec/latest/core/#hydra:Status">hydra:Status</a>
 * Created by ceefour on 10/15/15.
 */
public class Status implements StarterThing {

    private Object object;

    public Status() {
    }

    public Status(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "Status{" +
                "object=" + object +
                '}';
    }
}
