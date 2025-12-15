#!/usr/bin/env bash
CGO_ENABLED=0 GOOS=linux GOARCH=amd64 go build -a -ldflags '-w -s -extldflags \"-static\"' -o ./myapp ./src/go
go test ./tests/ -v -short