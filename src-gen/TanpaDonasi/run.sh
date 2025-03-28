#!/bin/bash
source ~/.zshrc  

cleanup() {
    pkill -P $$
    exit 1
}

trap cleanup SIGINT

java -cp aisco.product.tanpadonasi --module-path aisco.product.tanpadonasi -m aisco.product.tanpadonasi &

wait