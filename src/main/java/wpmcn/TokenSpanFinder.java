package wpmcn;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TokenSpanFinder {

   final protected Set<String> spanTags;
   protected TokenSpan currentSpan;

   public TokenSpanFinder(String... spanTags) {
      this(Arrays.asList(spanTags));
   }

   public TokenSpanFinder(Collection<String> spanTags) {
      this.spanTags = new HashSet<String>(spanTags);
   }

   @Override
   public String toString() {
      return currentSpan + " " + spanTags;
   }

   public TokenSpan handleTaggedToken(String token, String tag) {
      TokenSpan span = null;

      if (spanTags.contains(tag)) {
         if (null == currentSpan) {
            currentSpan = new TokenSpan(tag, token);
         } else if (tag.equals(currentSpan.getTag())) {
            currentSpan.add(token);
         } else {
            span = complete();
            currentSpan = new TokenSpan(tag, token);
         }
      } else {
         span = complete();
      }
      return span;
   }

   public TokenSpan complete() {
      TokenSpan span = null;

      if (null != currentSpan) {
         span = currentSpan;
         currentSpan = null;
      }
      return span;
   }
}
