package wpmcn;

import java.util.ArrayList;

public class TokenSpan extends ArrayList<String> {
   final private String tag;

   public TokenSpan(String tag, String... tokens) {
      this.tag = tag;
      for (String token : tokens)
         add(token);
   }

   @Override
   public String toString() {
      return tag + " " + super.toString();
   }

   public String getTag() {
      return tag;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      if (!super.equals(o)) return false;

      TokenSpan span = (TokenSpan) o;

      if (tag != null ? !tag.equals(span.tag) : span.tag != null) return false;

      return true;
   }

   @Override
   public int hashCode() {
      int result = super.hashCode();
      result = 31 * result + (tag != null ? tag.hashCode() : 0);
      return result;
   }
}
