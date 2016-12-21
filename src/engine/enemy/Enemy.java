package engine.enemy;

import engine.Type;

/**
 * Created by ezra on 11/19/16.
 */
public interface Enemy extends Type {

    double getSpeed();

    void setSpeed(double speed);

    double getHealth();

    void setHealth(double health);

    double getDamage();

    void setDamage(double damage);

    double getScore();

    void setScore(double score);

    double getMoney();

    void setMoney(double money);

    String getCollisionEffect();

    void setCollisionEffect(String collisionEffect);
}
