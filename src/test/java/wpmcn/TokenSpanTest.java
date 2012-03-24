package wpmcn;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

public class TokenSpanTest {
   private TokenSpan noTokens;
   private TokenSpan oneToken;
   private TokenSpan twoTokens;

   @Before
   public void setUp() throws Exception {
      noTokens = new TokenSpan("ORGANIZATION");
      oneToken = new TokenSpan("PERSON", "George");
      twoTokens = new TokenSpan("LOCATION", "Los", "Angeles");
   }

   @Test
   public void testToString() throws Exception {
      assertEquals("ORGANIZATION []", noTokens.toString());
      assertEquals("PERSON [George]", oneToken.toString());
      assertEquals("LOCATION [Los, Angeles]", twoTokens.toString());
   }

   @Test
   public void testEquals() throws Exception {
      assertTrue(noTokens.equals(new TokenSpan("ORGANIZATION")));
      assertTrue(oneToken.equals(new TokenSpan("PERSON", "George")));
      assertTrue(twoTokens.equals(new TokenSpan("LOCATION", "Los", "Angeles")));
   }

   @Test
   public void testNotEqual() throws Exception {
      assertFalse(noTokens.equals(oneToken));
      assertFalse(oneToken.equals(twoTokens));
      assertFalse(noTokens.equals(new TokenSpan("PERSON")));
      assertFalse(oneToken.equals(new TokenSpan("PERSON", "Mila")));
      assertFalse(twoTokens.equals(new TokenSpan("LOCATION", "Los", "Lobos")));
   }
}
