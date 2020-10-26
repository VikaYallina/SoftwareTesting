from tkinter import *
from tkinter import filedialog as fd
from tkinter import messagebox as mb
from pygame import mixer
from functools import partial
import datetime as datetime
import time
from threading import Thread


class Alarm:

    def __init__(self, window):
        mixer.init()
        window.geometry("230x270")
        window.title("Alarm")
        # times_frame = Frame(window)
        # times_frame.pack()

        # play_button = Button(window, text="Play", command=self.play)
        # play_button.place(x=20, y=30)
        # load_button = Button(window, text="Load", command=self.openMusicFile)
        # load_button.place(x=60, y=30)
        # test_button = Button(window, text="Test", command=self.time_select)
        # test_button.place(x=20, y=60)

        # load the alarm times
        self.music_file = open("MusicSaver.txt", "r").readline()
        self.picked_time = False
        self.alarm_times = []
        self.get_data()
        self.alarm_thread = AlarmThread()
        self.alarm_thread.setDaemon(True)
        self.alarm_thread.start()

        self.label_mon = Label(window, text="Mon:" + self.alarm_times[0]).grid(row=0, column=0)
        self.label_tue = Label(window, text="Tue: " + self.alarm_times[1]).grid(row=1, column=0)
        self.label_wed = Label(window, text="Wed: " + self.alarm_times[2]).grid(row=2, column=0)
        self.label_thu = Label(window, text="Thu: " + self.alarm_times[3]).grid(row=3, column=0)
        self.label_fri = Label(window, text="Fri: " + self.alarm_times[4]).grid(row=4, column=0)
        self.label_sat = Label(window, text="Sat: " + self.alarm_times[5]).grid(row=5, column=0)
        self.label_sun = Label(window, text="Sun: " + self.alarm_times[6]).grid(row=6, column=0)

        for k in range(7):
            Button(window, text="Change", command=partial(self.changetime_btn, k)).grid(row=k, column=1)
        Button(window, text="Test music", command=self.test).grid(row=7, column=0)
        Button(window, text="Change music", command=self.openMusicFile).grid(row=7, column=1)

    def get_data(self):
        with open("TimeSaver.txt") as f:
            self.alarm_times = [line.rstrip() for line in f]

    def changetime_btn(self, i):
        clock = Tk()
        clock.geometry("200x100")
        hour = StringVar()
        min = StringVar()
        sec = StringVar()

        hourTime = Entry(clock, textvariable=hour, width=15).grid(row=0, column=0)
        minTime = Entry(clock, textvariable=min, width=15).grid(row=0, column=1)
        secTime = Entry(clock, textvariable=sec, width=15).grid(row=0, column=2)

        Button(clock, text="Save", command=partial(self.save_changes, i, "12:14:20")).grid(
            row=1,
            column=1)
        clock.mainloop()

    def save_changes(self, i, new_time):
        print(i)
        self.alarm_times[i] = new_time
        file = open("TimeSaver.txt", "w")
        for al_time in self.alarm_times:
            file.write(al_time)
        self.alarm_thread.is_alive = False
        self.alarm_thread = AlarmThread()
        self.alarm_thread.setDaemon(True)
        self.alarm_thread.start()

    def openMusicFile(self):
        try:
            throwaway = fd.askopenfilename(filetypes=(("MP3 Files", "*.mp3"), ("All files", "*.*")))
            if throwaway:
                self.music_file = throwaway
                open("MusicSaver.txt", "w").write(throwaway)
                self.alarm_thread.is_alive = False
                self.alarm_thread = AlarmThread()
                self.alarm_thread.setDaemon(True)
                self.alarm_thread.start()
        except FileNotFoundError:
            mb.showerror("Warning!", "File was not selected")

    def play(self):
        if self.music_file:
            mixer.music.load(self.music_file)
            mixer.music.play()

    def test(self):
        self.play()
        mb.showinfo("Test!", "This is a test!")
        mixer.music.stop()


class AlarmThread(Thread):

    def __init__(self):
        self.is_alive = True
        mixer.init()
        super().__init__()
        self.is_time_set = False
        self.weekday = 0
        self.music_file = open("MusicSaver.txt", "r").readline()
        self.al_time = self.get_current_alarm()

    def play(self):
        if self.music_file:
            mixer.music.load(self.music_file)
            mixer.music.play()

    def get_current_alarm(self):
        al_time = ""
        with open("TimeSaver.txt") as f:
            alarm_times = [line.rstrip() for line in f]
        curr_time = datetime.datetime.now()
        if (alarm_times[curr_time.weekday()] != "--:--:--") and (
                curr_time.strftime("%H:%M:%S") < alarm_times[curr_time.weekday()]):
            al_time = alarm_times[curr_time.weekday()]
            self.weekday = curr_time.weekday()
            self.is_time_set = True
        else:
            i = curr_time.weekday()
            if i == 6:
                i = 0
            else:
                i += 1
            while (not self.is_time_set) and (i != curr_time.weekday()):
                if alarm_times[i] == "--:--:--":
                    if i == 6:
                        i = 0
                    else:
                        i += 1
                else:
                    al_time = alarm_times[i]
                    self.weekday = i
                    self.is_time_set = True
        print(al_time)
        return al_time

    def activate_alarm(self):
        self.play()
        mb.showinfo("Alarm!", "Time to wake up!")
        mixer.music.stop()

    def alarm(self):
        while self.is_alive:
            time.sleep(1)
            current_time = datetime.datetime.now()
            now = current_time.strftime("%H:%M:%S")
            date = current_time.strftime("%d/%m/%Y")
            print("Now " + now)
            print("Alarm " + self.al_time)
            print("")
            if (self.weekday == current_time.weekday()) and (now == self.al_time):
                self.activate_alarm()
                time.sleep(1)
                self.get_current_alarm()

    def run(self):
        if self.is_time_set:
            self.alarm()


if __name__ == "__main__":
    root = Tk()
    app = Alarm(root)
    root.mainloop()
