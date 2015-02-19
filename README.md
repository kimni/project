Programming Exercise:

Let’s Do Lunch
A short programming exercise Peggy Piranha and Samuel Amherst Salmon III (Sam), are old friends looking to get
together for lunch. They haven’t seen each other in ages, but are keen on crossing paths for lunch. But where could they meet? They’re flexible on the ultimate
destination, but there are few constraints. Peggy and Sam live in a fairly complicated watershed, with many rivers running
together and splitting apart. Sam has to keep moving upstream for spawning season.Peggy has dinner plans with a killer whale later in the day, and has to keep moving
downstream. Fortunately, we have a map given to us by the Piscine Post Office, with each junction marked with an address. Each segment of river is listed as pairs of
addresses, from upstream to downstream. Given the map and a list of possible starting locations for Peggy and Sam, give the list of locations where they could meet. Oh, and
there are a few places they must avoid passing through, with waterfalls, rapids, and other unpleasantness. Your job is to list the possible addresses where they could meet,
one per line and sorted by address.

Input/Output
Input and output are simple text, via standard input and standard output, respectively. Input will be provided in the format shown in the example below: the list of address pairs
forming the map, followed by the list of addresses to avoid, the list of possible starting locations for Peggy, and finally the list of possible starting locations for Sam.
Sample Input Sample Output Map (for reference)

Map:
a1 b1
a2 b1
a2 b2
a2 b3
b1 c1
b2 c2
b2 c3
b3 c3

Avoid:
b2

Peggy:
a2

Sam:
c2 c3

Output:
a2
b3
c3

Guidelines, corner cases, and other details
• Assume that the input is always well-formed - no need to guard against bad input.
• Assume that addresses are always single words made of letters and numbers,without spaces. Address names are always unique, the same word always
indicating the same location.
• If there are multiple locations to avoid, or multiple starting locations, they will be listed on a single line separated by spaces, e.g. ! a1 a2 a3
• If there are no locations to avoid, there will be a blank line, e.g.
Avoid:
Peggy:
a1
• Peggy and Sam will each always have at least one possible starting location.
• The output should be a list of locations, one per line, sorted alphabetically. If there are no possible meeting locations, there is no output either.
• Do not print anything other than the expected output - no headers, status messages or anything else (it would interfere with automated testing).
• If a starting location appears in the locations to avoid, the location must be avoided,and should not be passed through.
• Cycles in the map are possible. It is a complicated watershed, after all.
