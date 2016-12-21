package engine.ability;

import engine.Type;

public interface Ability extends Type {

    String getEffect ();

    void setEffect (String effect);

    double getRate ();

    void setRate (double rate);

}
