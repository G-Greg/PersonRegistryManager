import { describe, it, expect } from 'vitest';
import { render, fireEvent } from '@testing-library/react';
import ListPage from '../ListPage';
import { Person } from '../models/Person';
import React from 'react';
import { Address } from '../models/Address';
import { JSDOM } from 'jsdom';

const { window } = new JSDOM('<!doctype html><html><body></body></html>', {
    url: 'http://localhost/',
    referrer: 'http://localhost/',
    context: {},
    runScripts: 'dangerously',
    resources: 'usable',
    userAgent: 'node.js',
});

global.window = window;
global.document = window.document;

describe('ListPage', () => {
    it('renders the form', () => {
        const { getByText } = render(<ListPage />);

        getByText('List');
        getByText('Name');
        getByText('Birthday');
        getByText('Birthplace');
        getByText('TAJ');
        getByText('TAX Id');
        getByText('Email');
        getByText('Address(es)');
        getByText('Phone Number(s)');
    });

});