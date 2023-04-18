import "@testing-library/jest-dom";
import {render, fireEvent, waitFor, screen, getByRole} from '@testing-library/react'
import userEvent from "@testing-library/user-event";
// import dependencies
import { useState } from 'react';
import React from "react";
//import React = require("react")

// import API mocking utilities from Mock Service Worker
import {rest} from 'msw'
import {setupServer} from 'msw/node'


// add custom jest matchers from jest-dom
import '@testing-library/jest-dom'
import App from "../src/App";

import {vi} from 'vitest';
import { unescapeLeadingUnderscores } from "typescript";

describe("mocking fetch reqs", () => {


    const handlers = [
        rest.get("/", (req, res, ctx) => {
            // respond using a mocked JSON body
            return res(ctx.json({greeting: 'hello there'}))
            }),
    ]

    const server = setupServer(
        // capture "GET /greeting" requests
        ...handlers
    )

    // establish API mocking before all tests
    beforeAll(() => server.listen())
    // reset any request handlers that are declared as a part of our tests
    // (i.e. for testing one-time error scenarios)
    afterEach(() => server.resetHandlers())
    // clean up once the tests are done
    afterAll(() => server.close())

    beforeEach(() => {});

    test('overlay handles server error', async () => {
        server.use(
            // override the initial "GET /greeting" request handler
            // to return a 500 Server Error
            rest.get("/", (req, res, ctx) => {
                return res(ctx.status(500))
            }),
        ) 
    })
})