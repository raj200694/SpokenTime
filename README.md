# SpokenTime

Converts a 24-hour clock time (`HH:MM`) into its British colloquial spoken form.

```
Input:  12:00      Output: noon
Input:  00:00      Output: midnight
Input:  07:30      Output: half past seven
Input:  09:45      Output: quarter to ten
Input:  06:32      Output: six thirty-two
```

## Requirements

- Java 17+
- Maven 3.8+

## Building and running

Build and run the test suite:

```bash
mvn test
```

Package an executable jar:

```bash
mvn package
```

Run it against a time:

```bash
java -jar target/british-time.jar "14:32"
# twenty-five to three
```

Or pipe a time in via standard input:

```bash
echo "07:30" | java -jar target/british-time.jar
# half past seven
```

**Design**
The application deliberately keeps responsibilities small:

_TimeFormatter_ validates and parses the HH:mm input.
_TimeService_ contains the spoken-time business rules.
_ConvertsToWords_ owns the English number vocabulary.
_TimeApplication_ is the command-line entry point.

No framework is required because this is a small domain problem. Introducing Spring or a heavyweight design pattern would add complexity without providing a clear benefit.

Rule interpretation
The challenge examples contain an important boundary case:

06:32 -> six thirty-two
07:35 -> twenty-five to eight
Therefore this implementation treats :31 and :32 as direct minute readings, while :33 through :59 use the "to" form. This assumption is isolated in BritishTimeService and covered by tests so that it can be changed easily if the challenge owners clarify the intended rule.

Special cases:

00:00 -> midnight
12:00 -> noon
HH:00 -> <hour> o'clock
:15 -> quarter past
:30 -> half past
:45 -> quarter to
Testing
The test suite covers:

midnight and noon
exact hours
minutes past the hour
quarter past
half past
direct 31/32 minute readings
minutes to the next hour
twelve-hour boundary handling
parser validation
number-to-word conversion
every minute of every hour being convertible