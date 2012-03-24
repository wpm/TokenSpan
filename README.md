Token Span
==========

This project defines a labeled list of objects called a _token span_ along with a state machine that extracts contiguous token spans from a list of type-token pairs.

For example, given:

	George/PERSON Clooney/PERSON and/OTHER Mila/PERSON Kunis/PERSON live/OTHER in/OTHER Los/LOCATION Angeles/LOCATION

this extracts:

	PERSON		[George, Clooney]
	PERSON		[Mila, Kunis]
	LOCATION	[Los, Angeles]
