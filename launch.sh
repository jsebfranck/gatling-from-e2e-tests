
# Launch the recorder
node recorder/recorder.js &

# Launch the e2e test
casperjs --proxy=127.0.0.1:8000 e2e/casperjs/lequipe.js

# Stop the recorder
killall node

# Launch the generated simulation
cd gatling
./gradlew gatling -Psimulation=SavedRequestsSimulation
cd -
