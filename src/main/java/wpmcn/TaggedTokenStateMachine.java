package wpmcn;

import java.util.*;

public class TaggedTokenStateMachine {
   public class Span extends ArrayList<String> {
      final private String tag;

      public Span(String tag, String token) {
         this.tag = tag;
         add(token);
      }

      @Override
      public String toString() {
         return tag + " " + super.toString();
      }

      public String getTag() {
         return tag;
      }
   }

   final protected Set<String> spanTags;
   protected Span currentSpan;

   public TaggedTokenStateMachine(String... spanTags) {
      this(Arrays.asList(spanTags));
   }

   public TaggedTokenStateMachine(Collection<String> spanTags) {
      this.spanTags = new HashSet<String>(spanTags);
   }

   @Override
   public String toString() {
      return currentSpan + " " + spanTags;
   }

   public Span handleTaggedToken(String token, String tag) {
      Span span = null;

      if (spanTags.contains(tag)) {
         if (tag.equals(currentSpan.getTag())) {
            currentSpan.add(token);
         } else {
            span = complete();
            currentSpan = new Span(tag, token);
         }
      } else {
         span = complete();
      }
      return span;
   }

   public Span complete() {
      Span span = null;

      if (null != currentSpan) {
         span = currentSpan;
         currentSpan = null;
      }
      return span;
   }
}
