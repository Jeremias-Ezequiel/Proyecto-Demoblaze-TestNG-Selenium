BROWSER=$1

if [ -z "$BROWSER" ]; then
  BROWSER="chrome"
fi

./mvnw clean test -Dgroups="smoke" -Dbrowser=$BROWSER -Dheadless