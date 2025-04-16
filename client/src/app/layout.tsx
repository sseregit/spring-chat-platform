import type {Metadata} from "next";
import {GeistSans} from "geist/font/sans";
import "./globals.css";

export const metadata: Metadata = {
    title: "Spring Chat Platform",
    description: "Spring Chat Platform",
};

export const viewport = {
    width: "device-width",
    initialScale: 1,
    maximumScale: 1,
    userScalable: 1,
};

export default function RootLayout({
                                       children,
                                   }: Readonly<{
    children: React.ReactNode;
}>) {
    return (
        <html lang="en">
        <body className={GeistSans.className}>{children}</body>
        </html>
    );
}
