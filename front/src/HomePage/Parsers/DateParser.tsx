function DateParser(date: String): String {
  const split = date.split("-");
  const dateString: String = new Date(
    parseInt(split[0]),
    parseInt(split[1]) - 1,
    parseInt(split[2])
  ).toDateString();
  const dateSplit = dateString.split(" ");
  return dateSplit[0] + " " + dateSplit[1] + " " + dateSplit[2];
}

export default DateParser;
