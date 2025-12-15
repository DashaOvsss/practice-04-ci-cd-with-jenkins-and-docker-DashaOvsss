#!/usr/bin/env bash
apt-get update
apt-get install golang-go -y
go get github.com/gruntwork-io/terratest/modules/docker
go get github.com/stretchr/testify