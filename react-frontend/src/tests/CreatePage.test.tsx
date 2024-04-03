import CreatePage from '../CreatePage';
import { describe, it } from 'vitest'
import { render } from '@testing-library/react';
import { JSDOM } from 'jsdom';
import React from 'react';

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

describe('CreatePage', () => {
    it('renders the form', () => {
        const { getByText, getByLabelText } = render(<CreatePage />);

        getByText('Create');
        getByLabelText('Name');
        getByLabelText('Birthday');
        getByLabelText('Birthplace');
        getByLabelText('TAJ');
        getByLabelText('Tax ID');
        getByLabelText('Email');
        getByText('Addresses');
        getByText('Phone Numbers');
        getByText('Submit');
    });

});