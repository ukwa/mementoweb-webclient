Memento Web Client
==================

This is a very simple web client that exposes web archive holdings via the Memento protocol.

Given a URL, the client uses one or more known TimeGates to look up all known archival holdings, them summarises them and provides links.

One minor issue appears to be that the lanl.gov aggregate TimeGate does not appear to aggregate everything. Things like individual Wikipedia pages are (e.g. ) not coming up, despite the fact they are in the UK Web Archive and indeed reported by our TimeGate.  They are also clearly stored elsewhere, e.g. http://wayback.archive.org/web/*/http://en.wikipedia.org/wiki/Wikipedia:Wikipedia_is_an_encyclopedia

curl -I http://www.webarchive.org.uk/waybacktg/memento/timegate/http://en.wikipedia.org/wiki/Wikipedia:Wikipedia_is_an_encyclopedia

Leads via a redirect to the timebundle

http://www.webarchive.org.uk/waybacktg/ore/timemap/rdf/http://en.wikipedia.org/wiki/Wikipedia:Wikipedia_is_an_encyclopedia

Which is the RDF form, so need an RDF parser to extract the instances.

It appears the Aggregator is still using a proxy, so we should more Memento to production.

http://mementoweb.org/depot/proxy/BL/

curl "http://mementoproxy.lanl.gov/bl/timemap/http://en.wikipedia.org/wiki/Wikipedia:Wikipedia_is_an_encyclopedia"
<html><body><br/><center><table width='800px'><tr><td><div style='background-color: #e0e0e0; padding: 10px;'><br/><center><b>Error: 404</b></center>Unknown time map serialization<br/><br/></div></td></tr></table></body></html>


Note that 

curl  http://api.wayback.archive.org/list/timemap/link/http://en.wikipedia.org/wiki/Wikipedia:Wikipedia_is_an_encyclopedia

Works fine, so I wonder if the error on the BL proxy is throwing the whole aggregation off?