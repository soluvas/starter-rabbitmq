package org.soluvas.starter.rabbitmq.core;

/**
 * Created by ceefour on 21/04/2015.
 * @see <a href="https://schema.org/CommunicateAction">schema:CommunicateAction</a>
 */
public class CommunicateAction implements StarterThing {
    private String inLanguage;
    private String object;

    public CommunicateAction() {
    }

    public CommunicateAction(String inLanguage, String object) {
        this.inLanguage = inLanguage;
        this.object = object;
    }

    public String getInLanguage() {
        return inLanguage;
    }

    public void setInLanguage(String inLanguage) {
        this.inLanguage = inLanguage;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "CommunicateAction{" +
                "inLanguage='" + inLanguage + '\'' +
                ", object='" + object + '\'' +
                '}';
    }
}
