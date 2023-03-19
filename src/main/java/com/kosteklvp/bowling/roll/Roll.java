package com.kosteklvp.bowling.roll;

import static com.kosteklvp.bowling.BowlingUtils.MAX_NUMBER_OF_KNOCKED_PINS;
import static com.kosteklvp.bowling.roll.RollType.NONE;
import static com.kosteklvp.bowling.roll.RollType.NORMAL;
import static com.kosteklvp.bowling.roll.RollType.SPARE;
import static com.kosteklvp.bowling.roll.RollType.STRIKE;
import static com.kosteklvp.bowling.roll.RollType.TENTH_FRAME_SPARE;
import static com.kosteklvp.bowling.roll.RollType.TENTH_FRAME_STRIKE;
import static lombok.AccessLevel.PRIVATE;

import java.util.Objects;

import com.kosteklvp.utils.Utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = PRIVATE)
@Setter(PRIVATE)
@Getter
public class Roll {

  private Integer numberOfKnockedPins;
  private RollType type;

  public Integer getNumberOfKnockedPins() {
    return numberOfKnockedPins;
  }

  public char getSymbolOfKnockedPins() {
    if (RollType.NORMAL.equals(type)) {
      return Utils.toChar(Utils.nn(numberOfKnockedPins));
    }

    return type.getSymbol();
  }

  @Override
  public int hashCode() {
    return Objects.hash(numberOfKnockedPins, type);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Roll other = (Roll) obj;
    return Objects.equals(numberOfKnockedPins, other.numberOfKnockedPins) && type == other.type;
  }

  public static Roll of(Integer numberOfKnockedPins) {
    return instance(numberOfKnockedPins, NORMAL);
  }

  public static Roll none() {
    return instance(null, NONE);
  }

  public static Roll spareOf(Integer numberOfKnockedPins) {
    return instance(numberOfKnockedPins, SPARE);
  }

  public static Roll strike() {
    return instance(MAX_NUMBER_OF_KNOCKED_PINS, STRIKE);
  }

  public static Roll tenthFrameSpareOf(Integer numberOfKnockedPins) {
    return instance(numberOfKnockedPins, TENTH_FRAME_SPARE);
  }

  public static Roll tenthFrameStrike() {
    return instance(MAX_NUMBER_OF_KNOCKED_PINS, TENTH_FRAME_STRIKE);
  }

  private static Roll instance(Integer numberOfKnockedPins, RollType type) {
    Roll roll = new Roll();
    roll.setNumberOfKnockedPins(numberOfKnockedPins);
    roll.setType(type);

    return roll;
  }

}
