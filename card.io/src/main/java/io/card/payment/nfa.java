package io.card.payment;

/* nfa.java
 * See the file "LICENSE.md" for the full license governing this code.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

/**
 * Describes a credit card.
 *
 * @version 2.0
 */
public class nfa implements Parcelable {

    /**
     * Number of years into the future that a card expiration date is considered to be valid.
     */
    public static final int EXPIRY_MAX_FUTURE_YEARS = 15;

    private static final String TAG = nfa.class.getSimpleName();

    /**
     * 15 or 16 digit card number. All numbers, no spaces.
     */
    public String ag;

    /**
     * Month in two digit natural form. {January=1, ..., December=12}
     */
    public int bg = 0;

    /**
     * Four digit year
     */
    public int cc = 0;

    /**
     * Three or four character security code.
     */
    public String bc;

    /**
     * Billing postal code for card.
     */
    public String postalCode;

    /**
     * Cardholder name.
     */
    public String sc;

    // these should NOT be public
    String scanId;
    boolean flipped = false;
    int yoff;
    int[] xoff;

    // constructors
    public nfa() {
        xoff = new int[16];
        scanId = UUID.randomUUID().toString();
    }

    public nfa(String l, int i, int a, String m, String r, String sc) {
        this.ag = l;
        this.bg = i;
        this.cc = a;
        this.bc = m;
        this.postalCode = r;
        this.sc = sc;
    }

    // parcelable
    private nfa(Parcel src) {
        ag = src.readString();
        bg = src.readInt();
        cc = src.readInt();
        bc = src.readString();
        postalCode = src.readString();
        sc = src.readString();
        scanId = src.readString();
        yoff = src.readInt();
        xoff = src.createIntArray();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ag);
        dest.writeInt(bg);
        dest.writeInt(cc);
        dest.writeString(bc);
        dest.writeString(postalCode);
        dest.writeString(sc);
        dest.writeString(scanId);
        dest.writeInt(yoff);
        dest.writeIntArray(xoff);
    }

    public static final Parcelable.Creator<nfa> CREATOR = new Parcelable.Creator<nfa>() {

        @Override
        public nfa createFromParcel(Parcel source) {
            return new nfa(source);
        }

        @Override
        public nfa[] newArray(int size) {
            return new nfa[size];
        }
    };

    /**
     * @return The last four digits of the card number
     */
    public String getLastFourDigitsOfCardNumber() {
        if (ag != null) {
            int available = Math.min(4, ag.length());
            return ag.substring(ag.length() - available);
        } else {
            return "";
        }
    }

    /**
     * @return The card number string consisting of all but the last four digits replaced with
     * bullet ('&#8226;').
     */
    public String getRedactedCardNumber() {
        if (ag != null) {
            String redacted = "";
            if (ag.length() > 4) {
                redacted += String.format("%" + (ag.length() - 4) + "s", "").replace(' ',
                        '\u2022');
            }
            redacted += getLastFourDigitsOfCardNumber();
            return CreditCardNumber.formatString(redacted, false,
                    CardType.fromCardNumber(ag));
        } else {
            return "";
        }
    }

    /**
     * @return The type of card, detected from the number
     */
    public CardType getCardType() {
        return CardType.fromCardNumber(ag);
    }

    /**
     * @return A string suitable for display, with spaces inserted for readability.
     */
    public String getFormattedCardNumber() {
        return CreditCardNumber.formatString(ag);
    }

    /**
     * @return <code>true</code> indicates a current, valid date.
     */
    public boolean isExpiryValid() {
        return CreditCardNumber.isDateValid(bg, cc);
    }

    /**
     * @return a string suitable for writing to a log. Should not be displayed to the user.
     */
    @Override
    public String toString() {
        String s = "{" + getCardType() + ": " + getRedactedCardNumber();
        if (bg > 0 || cc > 0) {
            s += "  expiry:" + bg + "/" + cc;
        }
        if (postalCode != null) {
            s += "  postalCode:" + postalCode;
        }
        if (sc != null) {
            s += "  sc:" + sc;
        }
        if (bc != null) {
            s += "  cvvLength:" + ((bc != null) ? bc.length() : 0);
        }
        s += "}";
        return s;
    }
}
