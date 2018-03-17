public class Main {
        public static void main(String[] args) {

            Downloader downloader = new Downloader();
            downloader.download("https://data.wien.gv.at/daten/geo?service=WFS&request=GetFeature&version=1.1.0&typeName=ogdwien:AUFZUGOGD&srsName=EPSG:4326&outputFormat=csv", "AUFZUGOGD.csv");
            downloader.download("https://data.wien.gv.at/daten/geo?service=WFS&request=GetFeature&version=1.1.0&typeName=ogdwien:UBAHNHALTOGD&srsName=EPSG:4326&outputFormat=csv", "UBAHNHALTOGD.csv");

        }
}
