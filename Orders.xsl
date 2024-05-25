<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:fo="http://www.w3.org/1999/XSL/Format"
        version="1.0"
        xmlns:gen="http://lt.viko.eif/asilaikis/springsoap/gen"
        exclude-result-prefixes="gen">

    <xsl:output encoding="UTF-8" indent="yes" method="xml" standalone="no" omit-xml-declaration="no"/>
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="A4-portrait" page-height="297mm"
                                       page-width="210mm" margin-top="5mm"
                                       margin-bottom="5mm" margin-left="5mm"
                                       margin-right="5mm">
                    <fo:region-body margin-top="25mm" margin-bottom="20mm"/>
                    <fo:region-before region-name="xsl-region-before"
                                      extent="25mm" display-align="before"
                                      precedence="true"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="A4-portrait">
                <fo:flow flow-name="xsl-region-body" border-collapse="collapse" reference-orientation="0">
                    <xsl:apply-templates select="gen:getBookOrdersResponse"/>
                    <xsl:apply-templates select="gen:getBookResponse"/>
                    <xsl:apply-templates select="gen:getClientOrdersResponse"/>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

    <xsl:template match="gen:getBookOrdersResponse">
        <xsl:apply-templates select="gen:Books"/>
    </xsl:template>

    <xsl:template match="gen:getBookResponse">
        <xsl:apply-templates select="gen:Book"/>
    </xsl:template>

    <xsl:template match="gen:getClientOrdersResponse">
        <xsl:apply-templates select="gen:OrderList"/>
    </xsl:template>

    <xsl:template match="gen:OrderList">
        <fo:block>
            <fo:block text-align="center" display-align="center">
                <fo:external-graphic src="Client.png" content-width="scale-to-fit" width="30%"/>
            </fo:block>
            <fo:block font-weight="bold" text-align="center" color="darkgreen">Order</fo:block>
            <xsl:apply-templates select="gen:Order"/>
        </fo:block>
    </xsl:template>

    <xsl:template match="gen:Books">
        <fo:block>
            <fo:block text-align="center" display-align="center">
                <fo:external-graphic src="Client.png" content-width="scale-to-fit" width="30%"/>
            </fo:block>
            <fo:block font-weight="bold" text-align="center" color="darkgreen">Order</fo:block>
            <xsl:apply-templates select="gen:Order"/>
        </fo:block>
    </xsl:template>

    <xsl:template match="gen:Book">
        <fo:block>
            <fo:block text-align="center" display-align="center">
                <fo:external-graphic src="Book.png" content-width="scale-to-fit" width="30%"/>
            </fo:block>
            <fo:block font-weight="bold" text-align="center" color="darkgreen">Book</fo:block>
            <xsl:apply-templates select="gen:book"/>
        </fo:block>
    </xsl:template>

    <xsl:template match="gen:book">
            <fo:block>
                <fo:block font-weight="bold" text-align="center" color="darkgreen">Book information</fo:block>
                <fo:block text-align="center">Book ID: <xsl:value-of select="gen:id"/></fo:block>
                <fo:block text-align="center">Book Name: <xsl:value-of select="gen:Book_name"/></fo:block>
                <fo:block text-align="center">Author First Name: <xsl:value-of select="gen:First_name"/></fo:block>
                <fo:block text-align="center">Author Last Name: <xsl:value-of select="gen:Last_name"/></fo:block>
                <fo:block text-align="center">Price: <xsl:value-of select="gen:Price"/></fo:block>
            </fo:block>
    </xsl:template>

    <xsl:template match="gen:Order">
        <fo:block font-size="16pt" font-family="Arial, sans-serif" space-after="6mm">
            <fo:block font-weight="bold" text-align="center">
                <xsl:text>Order ID: </xsl:text><xsl:value-of select="@id"/>
            </fo:block>
            <fo:block font-weight="bold" text-align="center">
                <xsl:text>Book name: </xsl:text><xsl:value-of select="gen:book/gen:Book_name"/>
            </fo:block>
        </fo:block>
        <fo:block font-weight="bold" margin-top="15pt" font-size="14pt">
            <fo:block font-weight="bold" text-align="center" color="darkgreen">Client Information</fo:block>
            <fo:block font-weight="bold" text-align="center">
                <xsl:text>Client: </xsl:text><xsl:value-of select="gen:client/gen:First_name"/>
                <xsl:text> </xsl:text><xsl:value-of select="gen:client/gen:Last_name"/>
            </fo:block>
        </fo:block>
        <fo:block font-weight="bold" margin-top="15pt" font-size="14pt">
            <fo:block font-weight="bold" text-align="center" color="darkgreen">Book Information</fo:block>
            <fo:block text-align="center">Book ID: <xsl:value-of select="gen:book/gen:id"/></fo:block>
            <fo:block text-align="center">Book Name: <xsl:value-of select="gen:book/gen:Book_name"/></fo:block>
            <fo:block text-align="center">Author First Name: <xsl:value-of select="gen:book/gen:First_name"/></fo:block>
            <fo:block text-align="center">Author Last Name: <xsl:value-of select="gen:book/gen:Last_name"/></fo:block>
            <fo:block text-align="center">Price: <xsl:value-of select="gen:book/gen:Price"/></fo:block>
        </fo:block>
    </xsl:template>

</xsl:stylesheet>