package net.tiny.feature.entity;


import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import net.tiny.dao.SeparatedValues;

public class JobCategoryParserTest {

    @Test
    public void testParseMiddleData() throws Exception {
        Path path = Paths.get("src/test/resources/data/sql/insert_jobtype_full_middle.sql");
        assertTrue(Files.exists(path));
        Stream<String> lines = Files.lines(path);
        counter.getAndSet(16);
        ValuesParser parser = new ValuesParser(lines, 74, 2);
        parser.parse();
    }

    @Test
    public void testParseSmallData() throws Exception {
        Path path = Paths.get("src/test/resources/data/sql/insert_jobtype_full_small.sql");
        assertTrue(Files.exists(path));
        Stream<String> lines = Files.lines(path);
        counter.getAndSet(79);
        ValuesParser parser = new ValuesParser(lines, 94, 2);
        parser.parse();
    }

    @Test
    public void testParseRecord() throws Exception {
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
        String csv = "'02','0002','020002','財務・会計・経理'";
        //Record.middle(csv);
        System.out.println(Record.middle(csv).toString());

        csv = "'010001','0001','0100010001','企画営業【法人営業・個人営業】','企画営業（法人・個人）','企画営業'";
        //Record.small(csv);
        System.out.println(Record.small(csv).toString());
        csv = "'130001','0012','1300010012','獣医師','獣医師','獣医師'";
        System.out.println(Record.small(csv).toString());
    }

    @Test
    public void testMiddleCode() throws Exception {
        assertEquals(73, middleCode.code("130001"));
    }

    static final String REC_FORMAT = "%d,%d,%d,%d,\"%s\",\"%s\",\"%s\",\"%s\",,,\"2019-01-01 23:59:59\",\"2019-01-01 23:59:59\"";
    static final AtomicInteger counter = new AtomicInteger();
    static final MiddleCode middleCode = new MiddleCode(Paths.get("src/test/resources/data/csv/job_category.csv"));

    static class Record {
        int id, grade, orders, parent;
        String tree,name,label,summary;

        @Override
        public String toString() {
            return String.format(REC_FORMAT, id, grade, orders, parent,tree,name,label,summary);
        }

        public static Record middle(String csv) {
            Record rec = new Record();
            String code = csv.substring(13, 19);
            String text = csv.substring(22, csv.length()-1);
            int p = Integer.parseInt(code.substring(0, 2));
            if (p == 99) {
                p = 15;
            }
            int c = Integer.parseInt(code.substring(2));
            rec.id = counter.getAndIncrement();
            rec.grade = 1;
            rec.orders = c;
            rec.parent = p;
            rec.tree = String.format(",%d,", p);
            rec.name = rec.label = rec.summary =text;
            return rec;
        }

        public static Record small(String csv) {
            Record rec = new Record();
            String mc = csv.substring(1, 7);
            String code = csv.substring(17, 27);
            String[] array = csv.substring(29, csv.length()).split(",");
//            System.out.println(array[0].replaceAll("'", ""));
//            System.out.println(array[1].replaceAll("'", ""));
//            System.out.println(array[2].replaceAll("'", ""));

            int p = Integer.parseInt(code.substring(0, 2));
            if (p == 99) {
                p = 15;
            }
            int m = middleCode.code(mc);
            int c = Integer.parseInt(code.substring(6));
            rec.id = counter.getAndIncrement();
            rec.grade = 2;
            rec.orders = c;
            rec.parent = m;
            rec.tree = String.format(",%d,%d", p, m);
            rec.name = array[0].replaceAll("'", "");
            rec.label = array[1].replaceAll("'", "");
            rec.summary = array[2].replaceAll("'", "");
            return rec;
        }
    }

    static class MiddleCode {
        Map<String, Integer> index = new HashMap<>();
        MiddleCode(Path path) {
            try {
                Files.lines(path)
                    .skip(1L)
                    .forEach( new Consumer<String>() {
                        @Override
                        public void accept(String s) {
                            SeparatedValues csv = new SeparatedValues(s);
                            Integer id = new Integer(csv.get(0));
                            String p = csv.get(3);
                            if  (p!=null && !p.isEmpty()) {
                                String key = String.format("%02d%04d", Integer.parseInt(p), Integer.parseInt(csv.get(2)));
                                index.put(key, id);
                            }
                        }
                });
            } catch (IOException e) {
                throw new IllegalArgumentException(path.toString(), e);
            }
        }

        public int code(String mc) {
            if ("999999".equals(mc)) {
                return index.get("159999");
            }
            return index.get(mc);
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
            m = (offset == 74);
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
                System.out.println(Record.small(csv).toString());
            }
        }
    }
}
