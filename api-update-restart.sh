#!/usr/bin/env bash

image_name='kinetiqa-api'

cd kinetiqa/api || exit
docker stop "$(docker ps -q --filter ancestor="$image_name")"
git pull
git checkout master
git pull
docker build -t "$image_name" .
docker run --rm -it -d -p 443:8443/tcp --env DB_HOST=private-kinetiqa-postgresql-db-do-user-2264611-0.b.db.ondigitalocean.com --env DB_USER=doadmin --env DB_PASS=AVNS_wNURQm3hfFUhnBa --env KEYSTORE_PASS=KwP110WozaS037Jm --env PRIVATE_KEY_PASS=sdFSYE210Djjs0Qa --env KEYSTORE_PATH=/app/kinetiqa.bio.jks "$image_name"
