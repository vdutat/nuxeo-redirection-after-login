# nuxeo-redirection-after-login
===

## About this module

This module provides an override of the Seam bean `startupHelper` which redirects the user after login.

## Why this module

Redirection after login can be configured by setting the configuration variable `CustomStartupHelper.viewOrPath` to a view ID or a path to a document.

ex:

- redirection to the user's home dashboard

        CustomStartupHelper.viewOrPath=view_home

    Other possible values: `view_servers`, `view_domains`
        
- redirection to the workspaces' root of the default domain

        CustomStartupHelper.viewOrPath=/defaut-domain/workspaces
        
## Building

        mvn clean install

## Using

All you have to do is:

 - copy the bundle in `nxserver/plugins` or `nxserver/bundles`
 - restart the server
