package net.tiny.feature.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class PartCategoryParserTest {

    static final String ROOT_FORMAT = "%d,%d,%d,,\"%s\",\"%s\",\"%s\",\"%s\",,\"2019-01-01 23:59:59\",\"2019-01-01 23:59:59\"";
    static final String REC_FORMAT = "%d,%d,%d,%d,\"%s\",\"%s\",\"%s\",\"%s\",,\"2019-01-01 23:59:59\",\"2019-01-01 23:59:59\"";
    static final AtomicInteger counter = new AtomicInteger();

    @Test
    public void testParseLargeRecord() throws Exception {
        Record rec = new Record();
        rec.id = 3;
        rec.grade = 1;
        rec.orders = 1;
        rec.parent = 1;
        rec.tree = ",1,";
        rec.name = "name";
        rec.label = "label";
        rec.summary = "summary";
        System.out.println(rec.toString());
        String csv = "'06','事務','事務','一般事務、受付・秘書、コールセンター、電話応対、営業事務、経理事務、伝票整理・資料作成、DM作業など'";
        //Record.large(csv);
        System.out.println(Record.large(csv).toString());
    }

    @Test
    public void testParseMiddleRecord() throws Exception {
        String csv = "'0100080000','0008','01','1','カフェ・喫茶店','カフェ、喫茶店','カフェ、喫茶店'";
        //Record.middle(csv);
        System.out.println(Record.middle(csv).toString());

        csv = "'0600050000','0005','06','0','伝票整理・資料作成','伝票整理、資料作成','伝票整理、資料作成'";
        System.out.println(Record.middle(csv).toString());
    }

    @Test
    public void testParseLargeData() throws Exception {
        Path path = Paths.get("src/test/resources/data/sql/insert_jobtype_part_large.sql");
        assertTrue(Files.exists(path));
        Stream<String> lines = Files.lines(path);
        counter.getAndSet(1);
        ValuesParser parser = new ValuesParser(lines, 79, 2);
        parser.parse();
    }

    @Test
    public void testParseMiddleData() throws Exception {
        Path path = Paths.get("src/test/resources/data/sql/insert_jobtype_part_middle.sql");
        assertTrue(Files.exists(path));
        Stream<String> lines = Files.lines(path);
        counter.getAndSet(18);
        ValuesParser parser = new ValuesParser(lines, 99, 2);
        parser.parse();
    }

    static class Record {
        int id, grade, orders, parent;
        String tree,name,label,summary;

        @Override
        public String toString() {
            if (parent<1) {
                return String.format(ROOT_FORMAT, id, grade, orders, tree,name,label,summary);
            }
            return String.format(REC_FORMAT, id, grade, orders, parent,tree,name,label,summary);
        }

        public static Record large(String csv) {
            Record rec = new Record();
            String code = csv.substring(1, 3);
            String[] array = csv.substring(5, csv.length()).split(",");
            int c = Integer.parseInt(code);
            rec.id = counter.getAndIncrement();
            rec.grade = 0;
            rec.orders = c;
            rec.parent = -1;
            rec.tree = ",";
            rec.name = array[0].replaceAll("'", "").replaceAll("/", "・");
            rec.label = array[1].replaceAll("'", "");
            rec.summary = array[2].replaceAll("'", "");
            return rec;
        }

        public static Record middle(String csv) {
            Record rec = new Record();
            String code = csv.substring(1, 11);
            String[] array = csv.substring(29, csv.length()).split(",");

            rec.id = counter.getAndIncrement();
            int p = Integer.parseInt(code.substring(0, 2));
            int c = Integer.parseInt(code.substring(3, 6));
            rec.grade = 1;
            rec.orders = c;
            rec.parent = p;
            rec.tree = String.format(",%d,", p);
            rec.name = array[0].replaceAll("'", "").replaceAll("/", "・");
            rec.label = array[1].replaceAll("'", "");
            rec.summary = array[2].replaceAll("'", "");
            return rec;
        }
    }

    static class ValuesParser {
        Stream<String> lines;
        int offset, end;
        boolean m = true;
        ValuesParser(Stream<String> s, int h, int e) {
            lines = s;
            offset = h;
            end = e;
            m = (offset == 99);
        }

        public void parse() {
            lines.forEach(l -> parse(l));
        }

        public void parse(String line) {
            int last = line.length() - end;
            String csv = line.substring(offset, last);
            if (m) {
                System.out.println(Record.middle(csv).toString());
            } else {
                System.out.println(Record.large(csv).toString());
            }
        }
    }
}
