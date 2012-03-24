package wpmcn;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * A <code>TokenSpanFinder</code> finds sequences of contiguous tokens of the same type in a sequence of
 * (token, type) pairs.
 * <p/>
 * Call <code>nextToken</code> for each (token, type) pair in the input. Call <code>complete</code> when the input
 * is exhausted. Either of these methods will return either a {@link TokenSpan} or <code>null</code>.
 */
public class TokenSpanFinder {
   final protected Set<String> types;
   protected TokenSpan currentSpan;

   /**
    * @param types the types for which this class will build spans
    */
   public TokenSpanFinder(Collection<String> types) {
      this.types = new HashSet<String>(types);
   }

   public TokenSpanFinder(String... types) {
      this(Arrays.asList(types));
   }

   @Override
   public String toString() {
      return currentSpan + " " + types;
   }

   /**
    * Handles the next token in the input
    *
    * @param token the token
    * @param type  the token's type
    * @return a completed {@link TokenSpan} or <code>null</code>
    */
   public TokenSpan nextToken(String token, String type) {
      TokenSpan span = null;

      if (types.contains(type)) {
         if (null == currentSpan) {
            currentSpan = new TokenSpan(type, token);
         } else if (type.equals(currentSpan.getType())) {
            currentSpan.add(token);
         } else {
            span = complete();
            currentSpan = new TokenSpan(type, token);
         }
      } else {
         span = complete();
      }
      return span;
   }

   /**
    * Complete the current input
    *
    * @return a completed {@link TokenSpan} or <code>null</code>
    */
   public TokenSpan complete() {
      TokenSpan span = null;

      if (null != currentSpan) {
         span = currentSpan;
         currentSpan = null;
      }
      return span;
   }
}
