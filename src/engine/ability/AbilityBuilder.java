package engine.ability;

import engine.TypeBuilder;
import engine.weapon.BindableWeapon;
import engine.weapon.Weapon;
import engine.weapon.WeaponBuilder;
import engine.weapon.WeaponInitializer;

public interface AbilityBuilder extends TypeBuilder<Ability, AbilityBuilder>, BindableAbility { //TODO - Add bindable interface

    AbilityBuilder buildRate (double rate);

    AbilityBuilder buildEffect (String effect);

}
