package wpmcn;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class TokenSpanFinderTest {
   private String personLocationSentence;

   @Before
   public void setUp() throws Exception {
      personLocationSentence = "George/PERSON Clooney/PERSON and/OTHER Mila/PERSON Kunis/PERSON live/OTHER " +
            "in/OTHER Los/LOCATION Angeles/LOCATION";
   }

   @Test
   public void testNameAndLocation() throws Exception {
      TokenSpanFinder personLocation = new TokenSpanFinder("PERSON", "LOCATION");
      List<TokenSpan> expected = Arrays.asList(
            new TokenSpan("PERSON", "George", "Clooney"),
            new TokenSpan("PERSON", "Mila", "Kunis"),
            new TokenSpan("LOCATION", "Los", "Angeles")
      );
      List<TokenSpan> actual = handleSentence(personLocation, personLocationSentence);
      assertEquals(expected, actual);
   }

   private List<TokenSpan> handleSentence(TokenSpanFinder m, String sentence) {
      List<TokenSpan> spans = new ArrayList<TokenSpan>();
      TokenSpan span;
      for (String taggedToken : sentence.split("\\s+")) {
         String[] fields = taggedToken.split("/");
         span = m.handleTaggedToken(fields[0], fields[1]);
         if (null != span)
            spans.add(span);
      }
      span = m.complete();
      if (null != span)
         spans.add(span);
      return spans;
   }
}
