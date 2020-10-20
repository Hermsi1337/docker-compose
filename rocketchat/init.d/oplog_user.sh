#!/usr/bin/env bash

if [[ -z "${MONGODB_OPLOG_USER}" || -z ${MONGODB_OPLOG_PASSWORD} ]]; then
  exit 1
fi

mongo -u root -p "${MONGODB_ROOT_PASSWORD}" "admin" <<-EOJS
db.createUser({
  user: "${MONGODB_OPLOG_USER}",
  pwd: "${MONGODB_OPLOG_PASSWORD}",
roles: [{role: "read", db: "local"}]
})
EOJS