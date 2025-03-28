#!/bin/bash
source ~/.zshrc  

cleanup() {
    pkill -P $$
    exit 1
}

trap cleanup SIGINT

java -cp aisco.product.banktransfer --module-path aisco.product.banktransfer -m aisco.product.banktransfer &

wait