package wpmcn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaggedTokenStateMachine {
   public class Span extends ArrayList<String> {
   }

   final private Map<String, List<Span>> spans;
   final private Span currentSpan = new Span();
   private String currentSpanTag;

   public TaggedTokenStateMachine(String... spanTags) {
      spans = new HashMap<String, List<Span>>(spanTags.length);
      for (String spanTag : spanTags)
         spans.put(spanTag, new ArrayList<Span>());
   }

   public Map<String, List<Span>> getSpans() {
      return spans;
   }

   public void taggedToken(String token, String tag) {
      if (spans.containsKey(tag)) {
         if (tag.equals(currentSpanTag)) {
            appendToSpan(token);
         } else {
            completeSpan();
            beginSpan(token, tag);
         }
      } else {
         completeSpan();
      }
   }

   public void complete() {
      completeSpan();
   }

   private void beginSpan(String token, String tag) {
      currentSpanTag = tag;
      appendToSpan(token);
   }

   private void appendToSpan(String token) {
      currentSpan.add(token);
   }

   private void completeSpan() {
      if (!currentSpan.isEmpty()) {
         spans.get(currentSpanTag).add(currentSpan);
         currentSpanTag = null;
         currentSpan.clear();
      }
   }
}
