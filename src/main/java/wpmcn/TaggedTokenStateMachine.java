package wpmcn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaggedTokenStateMachine {
   final private Map<String, List<List<String>>> spans;
   final private List<String> currentSpan = new ArrayList<String>();
   private String currentSpanTag;

   public TaggedTokenStateMachine(String... spanTypes) {
      spans = new HashMap<String, List<List<String>>>(spanTypes.length);
      for (String spanType : spanTypes)
         spans.put(spanType, new ArrayList<List<String>>());
   }

   public Map<String, List<List<String>>> getSpans() {
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
