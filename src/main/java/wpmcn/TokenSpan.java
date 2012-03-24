package wpmcn;

import java.util.ArrayList;

/**
 * A sequence of tokens with with an associated type.
 */
public class TokenSpan extends ArrayList<String> {
   final private String type;

   public TokenSpan(String type, String... tokens) {
      this.type = type;
      for (String token : tokens)
         add(token);
   }

   @Override
   public String toString() {
      return type + " " + super.toString();
   }

   public String getType() {
      return type;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      if (!super.equals(o)) return false;

      TokenSpan span = (TokenSpan) o;

      if (type != null ? !type.equals(span.type) : span.type != null) return false;

      return true;
   }

   @Override
   public int hashCode() {
      int result = super.hashCode();
      result = 31 * result + (type != null ? type.hashCode() : 0);
      return result;
   }
}
