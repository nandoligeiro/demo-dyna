#!/bin/bash
curl -X POST http://localhost:8080/invoices
curl -X POST http://localhost:8080/invoices?fail=true
